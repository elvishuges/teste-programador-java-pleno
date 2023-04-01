<template>
  <div class="form-ordered">
    <b-form @submit="onSubmit">
      <b-form-group
        id="input-group-1"
        label="Número:"
        label-for="input-1"
        v-if="editing"
      >
        <b-form-input
          id="input-1"
          v-model="ordered.number"
          disabled
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Descrição" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="ordered.description"
          placeholder="Descrição do Produto"
          required
        ></b-form-input>
      </b-form-group>

      <div>
        <b-form-select
          v-model="ordered.products"
          :options="products"
          value-field="code"
          text-field="description"
          multiple
        ></b-form-select>
        {{ ordered.products }}
      </div>

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
import OrderedService from "./../services/OrderedService";
import ProductService from "./../services/ProductService";

export default {
  data() {
    return {
      ordered: {
        number: null,
        description: "",
        products: [],
        client: null,
      },
      products: [],
      productOptions: [],
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
      const options = {
        viewProducts: true,
      };
      const { data: ordered } = await OrderedService.getOrdered(
        params.id,
        options
      );

      ordered.products = ordered.products.map((p) => p.code);
      const { data: products } = await ProductService.getProducts();

      return {
        editing: true,
        ordered,
        products,
      };
    } else {
      const { data: products } = await ProductService.getProducts();
      return {
        editing: false,
        products,
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
      alert(JSON.stringify(this.ordered));
      event.preventDefault();
      if (this.editing) {
        await this.updateOrdered();
      } else {
        this.createOrdered();
      }
    },

    async updateOrdered() {
      try {
        await OrderedService.editOrdered(this.ordered.id, this.ordered);
        this.cleanProductInfos();
        this.showAlert("Pedido Editado com Sucesso");
        this.editing = false;
      } catch (error) {
        console.log("error", error);
      }
    },
    async createOrdered() {
      try {
        let orderedPost = this.ordered;
        delete orderedPost.code;
        await OrderedService.createOrdered(this.ordered);
        this.cleanProductInfos();
        this.showAlert("Produto Criado com Sucesso");
      } catch (error) {
        console.log("error", error);
      }
    },
    cleanProductInfos() {
      this.ordered = {
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

<style scoped></style>
