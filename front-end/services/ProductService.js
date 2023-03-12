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
  getProducts() {
    return apiProducts.get("/products");
  },
};
