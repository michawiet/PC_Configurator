import React, { useState, useEffect }  from 'react';
import { makeStyles, useTheme, AppBar, Toolbar, CssBaseline, Typography, IconButton, Grid, Button, Badge } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import MainTabsPanel from './MainTabsPanel';
import PartPickerTabs from './Tabs/PartPickerTabs';
import { useAuth } from "../AuthContext";
import axios from 'axios';
import ShoppingCartRoundedIcon from '@material-ui/icons/ShoppingCartRounded';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  toolbar: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
    marginTop: 75,
  },
}));

const MiniDrawer = (props) => {
  const classes = useStyles();
  const { children, value, index, ...other } = props;
  const [selectedTabs, setselectedTabs] = useState(0);
  const [error, setError] = useState("");
  const { currentUser, logout } = useAuth();
  const [itemCount, setItemCount] = useState(0);

  const handleChange = (event, newValue) => {
    setselectedTabs(newValue);
  };

  //trzeba to przenieść w inne miejsce  
  axios.post('http://localhost:8080/users/testPost?email=' + currentUser.email , {
    email: currentUser.email
  })
  .then(function (res) {
    console.log(res);
  })

  const handleClickOpenDialog = () => {
    history.push("/basket")
  };

  let history = useHistory();
  async function handleLogout() {
    setError("")
    try {
      await logout()
      history.push("/")
    } catch {
      setError("Failed to log out")
    }
  }

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
    <div className={classes.root}>
      <CssBaseline />
      <AppBar position="fixed">
        <Toolbar>
          <Grid container direction="row" justify="space-between" alignItems="center" spacing={0}>
            <Grid item>
              <Typography variant="h6" noWrap>
                Konfigurator PC
              </Typography>
            </Grid>
            <Grid item>
              <Grid container spacing={5}>
                <Grid item>
                  <IconButton color="inherit" onClick={handleClickOpenDialog}>  
                    <Badge badgeContent={itemCount} color="secondary">
                      <ShoppingCartRoundedIcon/>
                    </Badge>
                  </IconButton>
                </Grid>
                <Grid item>
                  <Button color="inherit" variant="outlined" onClick={handleLogout} >
                    Wyloguj się
                  </Button>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Toolbar>
        <MainTabsPanel selectedTabs={selectedTabs} setselectedTabs={setselectedTabs} />
      </AppBar>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <PartPickerTabs selectedTabs={selectedTabs} />
      </main>
    </div>
  );
}

export default MiniDrawer;
