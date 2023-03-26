<template>
  <div class="form-product">
    <b-form @submit="onSubmit">
      <b-form-group
        id="input-group-1"
        label="Código:"
        label-for="input-1"
        v-if="editing"
      >
        <b-form-input
          id="input-1"
          v-model="product.code"
          disabled
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Descrição" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="product.description"
          placeholder="Descrição do Produto"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-3" label="Unideda:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="product.unit"
          placeholder="Unidades"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-4" label="Valor:" label-for="input-4">
        <b-form-input
          id="input-4"
          v-model="product.value"
          v-cleave="cleaveOptions"
          placeholder="Valor R$"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">{{
        submitButtonText
      }}</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-alert
      :show="dismissCountDown"
      dismissible
      fade
      variant="warning"
      @dismiss-count-down="countDownChanged"
    >
      Produco Atualizado com Sucesso
    </b-alert>
  </div>
</template>

<script>
import ProductService from "./../services/ProductService";

export default {
  data() {
    return {
      product: {
        description: "",
        unit: "",
        value: null,
        code: "",
      },
      cleaveOptions: {
        numeral: true,
        numeralDecimalMark: ",",
        delimiter: ".",
        delimiterLazyShow: true,
      },
      editing: false,

      show: true,

      dismissSecs: 5,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      alertMessage: "",
    };
  },

  async asyncData({ params, $http }) {
    // método chamado antes de inicializar o component
    if (params.id) {
      const { data: product } = await ProductService.getProduct(params.id);
      return {
        editing: true,
        product,
      };
    }
  },

  computed: {
    submitButtonText() {
      return this.editing ? "Editar" : "Cadastrar";
    },
  },
  methods: {
    async onSubmit(event) {
      alert(JSON.stringify(this.product));
      event.preventDefault();
      if (this.editing) {
        await this.updateProduct();
      } else {
        this.createProduct();
      }
    },

    async updateProduct() {
      try {
        await ProductService.editProduct(this.product.id, this.product);
        this.cleanProductInfos();
        this.showAlert("Produto Editado com Sucesso");
        this.editing = false;
      } catch (error) {
        console.log("error", error);
      }
    },
    async createProduct() {
      try {
        let productPost = this.product;
        delete productPost.code;
        await ProductService.createProduct(this.product);
        this.cleanProductInfos();
        this.showAlert("Produto Criado com Sucesso");
      } catch (error) {
        console.log("error", error);
      }
    },
    cleanProductInfos() {
      this.product = {
        description: "",
        unit: "",
        value: null,
        code: "",
      };
    },
    showAlert(message) {
      this.alertMessage = message;
      this.dismissCountDown = this.dismissSecs;
    },
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
  },
};
</script>

<style scoped>
.form-product {
}
</style>
