import React, { useState, useEffect }  from 'react';
import { makeStyles, useTheme, AppBar, Toolbar, CssBaseline, Typography, IconButton, Grid, Button, Badge } from '@material-ui/core';
import { useHistory } from "react-router-dom";
import MainTabsPanel from './MainTabsPanel';
import PartPickerTabs from './Tabs/PartPickerTabs';
import { useAuth } from "../AuthContext";
import axios from 'axios';
import ShoppingCartOutlinedIcon from '@material-ui/icons/ShoppingCartOutlined';
import BasketBadgedButton from './Basket/BasketBadgedButton';

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
              <Grid container direction="row" justify="space-between" alignItems="center" spacing={5}>
                <Grid item>
                  <BasketBadgedButton/>
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
