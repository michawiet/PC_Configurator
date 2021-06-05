import React, { useState, useEffect }  from 'react';
import { Button, Badge } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import ShoppingCartOutlinedIcon from '@material-ui/icons/ShoppingCartOutlined';

function BasketBadgedButton() {
  const [itemCount, setItemCount] = useState(0);

  
  let history = useHistory();

  const handleClickOpenDialog = () => {
    history.push("/koszyk")
  };

  useEffect(() => {
    function updateItemBasketCount() {
      const itemsString = localStorage.getItem("cart");
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
      <Button
        color="inherit"
        variant="outlined"
        disableElevation
        onClick={handleClickOpenDialog}
        startIcon={<Badge anchorOrigin={{
          vertical: 'top',
          horizontal: 'right',
        }} badgeContent={itemCount} color="secondary" ><ShoppingCartOutlinedIcon/></Badge>}
      >
        Koszyk
      </Button>
    </div>
  )
}

export default BasketBadgedButton
