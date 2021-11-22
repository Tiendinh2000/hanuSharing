import "./App.css";
import React, { useState, useEffect } from "react";
import axios from "axios";


// Token
const token =
  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlkIjoxNiwiZXhwIjoxNjM3NjI4MDU2LCJpYXQiOjE2Mzc1NjgwNTZ9.cc1VzbQhm1nGa9_H4hXFrAth96sBUNWBRgsUKUlgJso";
const AuthToken = "Bearer " + token;

const GetItemsList = () => {
const [itemList,setItemList]=useState([]);

  const fetchItemList = () => {
    axios.get("http://localhost:8081/user/api/items/get", {
        headers: { Authorization: AuthToken }
      })
      .then(res => {
        console.log(res);
     
        setItemList(res.data);
      });
  };

  useEffect(() => {
    fetchItemList();
  }, []);

  return itemList.map((item,index)=>{
    return(<div key={index}>
        <h1>{item.item_id}</h1>
        <h1>{item.name}</h1>
        <h2>{item.price}</h2>
      </div>);
  });
};

function App() {
  return (
    <div className="App">
      <GetItemsList />
      Hello
    </div>
  );
}

export default App;
