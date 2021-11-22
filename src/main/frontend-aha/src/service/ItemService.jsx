import axios from "axios";

// Token
const token =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlkIjoxNiwiZXhwIjoxNjM3NjI4MDU2LCJpYXQiOjE2Mzc1NjgwNTZ9.cc1VzbQhm1nGa9_H4hXFrAth96sBUNWBRgsUKUlgJso";
const AuthToken = "Bearer " + token;
//base url
const BASE_URL="http://localhost:8081/user/api/items/get";

class ItemService{
    getItemList(){
        return axios.get(BASE_URL, {
          headers: { Authorization: AuthToken },
        });
    }
}

export default new ItemService();




