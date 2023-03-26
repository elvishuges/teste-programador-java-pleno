import apiOrdereds from "~/plugins/axios";

export default {
  getOrdereds(id) {
    return apiOrdereds.get(`/ordered/${id}`);
  },
  getOrdereds() {
    return apiOrdereds.get("/ordereds");
  },
  editOrdereds(id, orderedData) {
    return apiOrdereds.put(`/ordered/${id}`, orderedData);
  },
  deleteOrdereds(id) {
    return apiOrdereds.delete(`/ordered/${id}`);
  },
  createOrdereds(ordered) {
    return apiOrdereds.post("/ordered", ordered);
  },
};
