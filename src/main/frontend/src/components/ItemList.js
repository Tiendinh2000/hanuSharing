import React, { useEffect, useState } from "react";
import itemApi from "../api/itemApi";

function ItemList() {
  const [itemList, setItemList] = useState([]);

  useEffect(() => {
    const fetchItemList = async () => {
      const response = await itemApi.getAll();

      console.log(response.data);
      setItemList(response.data);
    };

    fetchItemList();
  }, []);

  console.log("state: ");
  console.log(itemList);

  return (
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">First</th>
          <th scope="col">Last</th>
          <th scope="col">Handle</th>
        </tr>
      </thead>
      {itemList.map((item) => {
        return (
          <tbody>
            <tr key={item.item_id}>
              <th scope="row">{item.item_id}</th>
              <td>{item.name}</td>
              <td>{item.price}</td>
            </tr>
          </tbody>
        );
      })}
    </table>
  );
}

// const Cat=({data})=>{
//     return(<h1 key={data.id}>{data.type}</h1>);
// }

export default ItemList;
