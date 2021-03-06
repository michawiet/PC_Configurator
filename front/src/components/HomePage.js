import React, { useState } from 'react'
import { makeStyles, AppBar, Menu, MenuItem, Toolbar, CssBaseline, Typography, Grid, Button, Container, Divider } from '@material-ui/core';
import BasketBadgedButton from './Cart/CartBadgedButton';
import { Switch, useHistory } from 'react-router-dom';
import RouteWithSubRoutes from './RouteWithSubRoutes';
import { useAuth } from "../AuthContext";
import MainTabsPanel from './HomePageElements/AppBarButtons';
import AccountCircleOutlinedIcon from '@material-ui/icons/AccountCircleOutlined';

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
    marginTop: 0,
  },
  icon: {
    width: 24,
    height: 24,
  },
}));

function HomePage({ routes }) {
  //menu
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const classes = useStyles();
  const [error, setError] = useState("");
  const { currentUser, logout } = useAuth();
  let history = useHistory();

  async function handleLogout() {
    setError("");
    try {
      await logout();
      history.push("/konfigurator");
    } catch {
      setError("Failed to log out");
    }
  }

  return (
    <div>
      <CssBaseline />
      <AppBar elevation={0} position="sticky">
        <Toolbar>
          <Grid container direction="row" justify="space-between" alignItems="center" spacing={0}>
            <Grid item>
              <Typography variant="h6" noWrap>
                Konfigurator PC
              </Typography>
            </Grid>
            <Grid item>
              <Grid container direction="row" justify="space-between" alignItems="center" spacing={4}>
                <Grid item>
                  <Button color="inherit" onMouseOver={handleClick} onMouseLeave={handleClick}
                  disableElevation variant="outlined" startIcon={<AccountCircleOutlinedIcon/>}>
                    Twoje konto
                  </Button>
                  {currentUser ? <><Menu
                    id="fade-menu"
                    anchorEl={anchorEl}
                    getContentAnchorEl={null}
                    anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
                    transformOrigin={{ vertical: "top", horizontal: "center" }}
                    keepMounted
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{ onMouseLeave: handleClose }}
                  >
                    <MenuItem onClick={handleClose, () => history.push('/zamowienia')}>Twoje zam??wienia</MenuItem>
                    <Divider /><MenuItem onClick={handleClose, handleLogout}>Wyloguj si??</MenuItem></Menu></> :
                    <><Menu
                    id="fade-menu"
                    anchorEl={anchorEl}
                    getContentAnchorEl={null}
                    anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
                    transformOrigin={{ vertical: "top", horizontal: "center" }}
                    keepMounted
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{ onMouseLeave: handleClose }}
                  >
                    <MenuItem onClick={handleClose, () => history.push('/zamowienia')}>Twoje zam??wienia</MenuItem>
                    <Divider /><MenuItem onClick={handleClose, () => history.push('/logowanie') }>Zaloguj si??</MenuItem>
                    <MenuItem onClick={handleClose, () => history.push('/rejestracja')}>Rejestracja</MenuItem></Menu></>}
                </Grid>
                <Grid item>
                  <BasketBadgedButton/>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
      <AppBar position="static">
        <MainTabsPanel/>
      </AppBar>
      {/* WA??NE ??EBY TO BY??O NA DOLE, TAM SI?? TO WY??WIETLA CO POTRZEBA PEPEGA XD */}
      <Container fixed className={classes.content}>
        <Switch>
          {routes.map((route, i) => (
            <RouteWithSubRoutes key={i} {...route} />
			  	))}
        </Switch>
      </Container>
    </div>
  )
}

export default HomePage
