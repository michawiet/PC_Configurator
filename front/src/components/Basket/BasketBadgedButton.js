import React, { useState, useEffect }  from 'react';
import { IconButton, Badge } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import ShoppingCartOutlinedIcon from '@material-ui/icons/ShoppingCartOutlined';

function BasketBadgedButton() {
  const [itemCount, setItemCount] = useState(0);

  
  let history = useHistory();

  const handleClickOpenDialog = () => {
    history.push("/basket")
  };

  useEffect(() => {
    function updateItemBasketCount() {
      const itemsString = localStorage.getItem("basket");
      var count = 0;
      if(itemsString) {
        const items = JSON.parse(itemsString);
        for(const item of items) {
          count += item.quantity;
        }
      }
      setItemCount(count);
    }
    updateItemBasketCount();
    window.addEventListener('storage', updateItemBasketCount);
    return () => {
      window.removeEventListener("storage", updateItemBasketCount);
    }
  }, [])

  return (
    <div>
      <IconButton color="inherit" onClick={handleClickOpenDialog}>  
        <Badge badgeContent={itemCount} color="secondary">
          <ShoppingCartOutlinedIcon/>
        </Badge>
      </IconButton>
    </div>
  )
}

export default BasketBadgedButton
