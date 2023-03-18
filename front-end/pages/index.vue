<template>
  <div>
    <b-table :items="items" :fields="fields" striped responsive="sm">
      <template #cell(show_details)="row">
        <b-button size="sm" @click="toggleEdit(row.item)" class="mr-2">
          Editar
        </b-button>
        <b-button size="sm" @click="toggleDelete(row.item)" class="mr-2">
          Deletar
        </b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
import ProductService from "./../services/ProductService";
export default {
  data() {
    return {
      fields: ["first_name", "last_name", "show_details"],
      items: [
        {
          isActive: true,
          age: 40,
          first_name: "Dickerson",
          last_name: "Macdonald",
        },
        { isActive: false, age: 21, first_name: "Larsen", last_name: "Shaw" },
        {
          isActive: false,
          age: 89,
          first_name: "Geneva",
          last_name: "Wilson",
          _showDetails: true,
        },
        { isActive: true, age: 38, first_name: "Jami", last_name: "Carney" },
      ],
      products: [],
    };
  },
  async mounted() {
    await this.fetchSomething();
  },
  methods: {
    toggleEdit() {
      this.$router.push({ name: "createEditProduct" });
    },
    toggleDelete() {
      console.log("edit");
    },
    async fetchSomething() {
      const products = await ProductService.getProducts();
      this.products = products.data;
    },
  },
};
</script>
