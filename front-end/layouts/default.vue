<template>
  <div class="default-page bg-gray-100">
    <Nav />
    <div class="nuxt"><Nuxt /></div>
  </div>
</template>

<script>
import Nav from "../components/nav.vue";
export default {
  components: {
    Nav,
  },
};
</script>

<style>
.nuxt {
  padding: 20px;
}
</style>
