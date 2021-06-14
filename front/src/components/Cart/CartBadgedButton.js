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
        currentUser.getIdToken(/* forceRefresh */ true).then(function(idToken) {
          axios.post("http://localhost:8080/cart/getItemCount?token=" + idToken)
          .then(res => setItemCount(res.data))
          .catch(() => console.log("item count post failed"));
        }).catch(function(error) {
          // Handle error
        });
        
      } else {
        console.log("no nie jestem niby zalogowany");
      }
    }
    updateItemBasketCount();
    window.addEventListener('focus', updateItemBasketCount);
    window.addEventListener('cartUpdate', updateItemBasketCount);
    return () => {
      window.removeEventListener('cartUpdate', updateItemBasketCount);
      window.removeEventListener('focus', updateItemBasketCount);
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
