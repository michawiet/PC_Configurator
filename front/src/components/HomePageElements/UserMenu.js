import React, { useState } from 'react'

function UserMenu({user}) {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div>
      <Menu
        id="simple-menu"
        keepMounted
        open={false}
      >
        {user ? (<><MenuItem>Zaloguj się</MenuItem>
        <MenuItem>Załóż konto</MenuItem></>) 
        : <MenuItem>Wyloguj się</MenuItem>}
        <MenuItem>Twoje zamówienia</MenuItem>
      </Menu>
    </div>
  )
}

export default UserMenu
