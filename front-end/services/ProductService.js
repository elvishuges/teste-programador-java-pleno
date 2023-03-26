import api from "~/plugins/axios";

export default {
  getProduct(id) {
    return api.get(`/product/${id}`);
  },
  getProducts() {
    return api.get("/products");
  },
  editProduct(id, productData) {
    return api.put(`/product/${id}`, productData);
  },
  deleteProduct(id) {
    return api.delete(`/product/${id}`);
  },
  createProduct(product) {
    return api.post("/product", product);
  },
};
