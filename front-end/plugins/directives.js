import Vue from "vue";
import Cleave from "cleave.js";

Vue.directive("cleave", {
  inserted: (el, binding) => {
    el.cleave = new Cleave(el, binding.value || {});
  },
  update: (el) => {
    const event = new Event("input", { bubbles: true });

    setTimeout(function () {
      const result = el.cleave.properties.result;
      if (result !== "R$ ") el.value = result;
      el.dispatchEvent(event);
    }, 100);
  },
});
