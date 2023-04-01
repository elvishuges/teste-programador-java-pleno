<template>
  <div>
    <div class="actions">
      <div></div>
      <b-button @click="toggleCreate">Cadastrar</b-button>
    </div>
    <b-table :items="ordereds" :fields="fields" striped responsive="sm">
      <template #cell(actions)="row">
        <b-button size="sm" @click="toggleEdit(row.item)" class="mr-2">
          Editar
        </b-button>
        <b-button size="sm" @click="toggleDelete(row.item)" class="mr-2">
          Deletar
        </b-button>
      </template>
      <template #cell(date)="row">
        {{ formateDate(row.value) }}
      </template>
    </b-table>
  </div>
</template>

<script>
import OrderedService from "./../services/OrderedService";
import { americanToBrazilianDate } from "./../utils/formaters";
export default {
  // https://github.com/stivenramireza/nuxt-learning/blob/master/nuxt-crud/pages/blog/_id.vue
  // https://nuxtjs.org/docs/features/data-fetching/
  data() {
    return {
      fields: ["number", "date", "description", "actions"],
      ordereds: [],
      boxTwo: "",
      deletingItem: null,
    };
  },
  async mounted() {
    await this.loadOrdereds();
  },
  methods: {
    toggleEdit(item) {
      this.$router.push({
        name: "createEditOrdereds",
        params: { id: item.id },
      });
    },
    toggleCreate() {
      this.$router.push({ name: "createEditOrdereds" });
    },
    async deleteItem(item) {
      try {
        await OrderedService.deleteOrdered(item.id);
      } catch (error) {
      } finally {
        this.loadOrdereds();
      }
    },
    async loadOrdereds() {
      const options = {
        viewProducts: true,
      };
      const ordereds = await OrderedService.getOrdereds(options);
      this.ordereds = ordereds.data;
    },
    formateDate(date) {
      return americanToBrazilianDate(date);
    },
    toggleDelete(item) {
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

<style scoped>
.actions {
  display: flex;
  justify-content: space-between;
  padding: 10px;
}
</style>
