import axios from "axios";

const pai = axios.create({
  baseURL: `http://localhost:8080/api`,
  whithCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default pai;
