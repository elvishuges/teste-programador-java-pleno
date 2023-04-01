import apiOrdereds from "~/plugins/axios";

export default {
  getOrdered(id) {
    return apiOrdereds.get(`/ordered/${id}`);
  },
  getOrdereds(options) {
    return apiOrdereds.get(`/ordereds`, { params: options });
  },
  editOrdered(id, orderedData) {
    return apiOrdereds.put(`/ordered/${id}`, orderedData);
  },
  deleteOrdered(id) {
    return apiOrdereds.delete(`/ordered/${id}`);
  },
  createOrdered(ordered) {
    return apiOrdereds.post("/ordered", ordered);
  },
};
