import axiosClient from "./axiosClient";


const itemApi ={
    getAll: ()=>{
        const url='/user/items/get';
        return axiosClient.get(url);
},

}

export default itemApi;