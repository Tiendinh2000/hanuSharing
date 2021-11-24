import React, { useEffect, useState } from "react";
import itemApi from "./api/itemApi";
import "./App.css";
import ItemList from "./components/ItemList";

function App() {
  // const [itemList, setItemList] = useState([]);

  // useEffect(() => {
  //   const fetchItemList = async () => {
  //     const response = await itemApi.getAll();

  //     console.log(response.data);
  //     setItemList(response.data);
  //   };

  //   fetchItemList();
  // }, []);

  // console.log("state: ");
  // console.log(itemList);

  // return itemList.map((item, index) => {
  //   return (
  //     <div key={index}>
  //       <h1>{item.name}</h1>
  //     </div>
  //   );
  // });

  return(
    <div>
      <ItemList/>
    </div>
  )
}

export default App;
