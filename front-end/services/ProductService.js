import axios from "axios";

const apiProducts = axios.create({
  baseURL: `http://localhost:8080/api`,
  whithCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default {
  getProduct(id) {
    return apiProducts.get(`/product/${id}`);
  },
  getProducts() {
    return apiProducts.get("/products");
  },
  editProduct(id, productData) {
    return apiProducts.put(`/product/${id}`, productData);
  },
  deleteProduct(id) {
    return apiProducts.delete(`/product/${id}`);
  },
  createProduct(product) {
    return apiProducts.post("/product", product);
  },
};
