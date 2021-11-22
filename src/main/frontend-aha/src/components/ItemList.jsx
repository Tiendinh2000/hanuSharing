//import React,  {Component, useState, useEffect } from "react";
import React, { Component } from "react";
import PropTypes from "prop-types";
import ItemService from "../service/ItemService";

class ItemList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
    };
  }

  componentDidMount(){
  ItemService.getItemList().then(res=>{
      this.setState({items: res.data});
  });
  }

  render() {
    return (
      <div>
        <h2 className="text-center">Item List</h2>
        <div className="row">
          <table className="">
           
              <thead>
              <tr>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                </tr>
              </thead>
            

           
              <tbody>
                {
                this.state.items.map(item => 
                  <tr key={item.item_id}>
                      <td>{item.item_id}</td>
                      <td>{item.name}</td>
                      <td>{item.price}</td>
                  </tr>
                    
                )
                }
              </tbody>
           

          </table>
        </div>
      </div>
    );
  }
}


export default ItemList;

// const ItemList = () => {
//     const [itemList, setItemList] = useState([]);

//     const fetchItemList = () => {

//     };

//     useEffect(() => {
//       fetchItemList();
//     }, []);

//     return itemList.map((item, index) => {
//       return (
//         <tr key={index}>
//           <td>{item.item_id}</td>
//           <td>{item.name}</td>
//           <td>{item.price}</td>
//         </tr>
//       );
//     });
//   };
//   export default ItemList;
