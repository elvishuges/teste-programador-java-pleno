<template>
  <div>
    <b-table :items="ordereds" :fields="fields" striped responsive="sm">
      <template #cell(actions)="row">
        <b-button size="sm" @click="toggleEdit(row.item)" class="mr-2">
          Editar
        </b-button>
        <b-button size="sm" @click="onDeleteItemClick(row.item)" class="mr-2">
          Deletar
        </b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import OrderedService from "./../services/OrderedService";
export default {
  // https://github.com/stivenramireza/nuxt-learning/blob/master/nuxt-crud/pages/blog/_id.vue
  // https://nuxtjs.org/docs/features/data-fetching/
  data() {
    return {
      fields: ["number", "date", "description"],
      ordereds: [],
      boxTwo: "",
      deletingItem: null,
    };
  },
  async mounted() {
    await this.loadProducts();
  },
  methods: {
    toggleEdit(item) {
      this.$router.push({ name: "createEditProduct", params: { id: item.id } });
    },
    async deleteItem(item) {
      try {
        await OrderedService.deleteProduct(item.id);
      } catch (error) {
      } finally {
        this.loadProducts();
      }
    },
    async loadProducts() {
      const ordereds = await OrderedService.getOrdereds();
      console.log("pr", ordereds);
      this.ordereds = ordereds.data;
    },
    onDeleteItemClick(item) {
      this.deletingItem = item;
      this.boxTwo = "";
      this.$bvModal
        .msgBoxConfirm("Deseja realmente deletar o item ?", {
          title: "Confirmar",
          size: "sm",
          buttonSize: "sm",
          okVariant: "danger",
          okTitle: "YES",
          cancelTitle: "NO",
          footerClass: "p-2",
          hideHeaderClose: false,
          centered: true,
        })
        .then((value) => {
          value ? this.deleteItem(this.deletingItem) : "";
        })
        .catch((err) => {
          // An error occurred
        });
    },
  },
};
</script>
