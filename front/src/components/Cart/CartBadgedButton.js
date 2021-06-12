import React, { useState, useEffect }  from 'react';
import { Button, Badge } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import ShoppingCartOutlinedIcon from '@material-ui/icons/ShoppingCartOutlined';
import axios from "axios";
import { useAuth } from "../../AuthContext";

function BasketBadgedButton() {
  const [itemCount, setItemCount] = useState(0);
  const { currentUser } = useAuth();
  let history = useHistory();

  const handleClickOpenDialog = () => {
    history.push("/koszyk")
  };

  //effect for updating badge

  useEffect(() => {
    function updateItemBasketCount() {
      if(currentUser) {
        axios.post("http://localhost:8080/cart/getItemCount?email=" + currentUser.email)
        .then(res => setItemCount(res.data))
        .catch(() => console.log("item count post failed"));
      }
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
