import React, { useRef, useState, useEffect } from 'react';
import {Avatar, Button, CssBaseline, TextField, Grid, Box, Typography, makeStyles, Container} from '@material-ui/core';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import { useHistory } from "react-router-dom";
import { useAuth } from "../../AuthContext"
import firebase from "firebase/app";
import Alert from '@material-ui/lab/Alert';
import axios from 'axios';
import {ReactComponent as GoogleIcon} from "../../icons/btn_google_light_normal_ios.svg";

const useStyles = makeStyles((theme) => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignUp() {
  const emailRef = useRef()
  const passwordRef = useRef()
  const passwordConfirmRef = useRef()
  const { signup } = useAuth()
  const [error, setError] = useState("")
  const [loading, setLoading] = useState(false)
  const classes = useStyles();
  const [loginSuccess, setLoginSuccess] = useState(false);
  let history = useHistory();

  useEffect(() => {
    if(loginSuccess) {
      history.push("/konfigurator");
    }
  }, [loginSuccess]);

  async function handleSubmit(e) {
    e.preventDefault()
    if(emailRef.current.value === "") {
      return setError("Podaj adres email!");
    }
    if(passwordRef.current.value === "") {
      return setError("Wprowadź hasło!");
    }
    if(passwordConfirmRef.current.value === "") {
      return setError("Wprowadź hasło!");
    }
    if (passwordRef.current.value !== passwordConfirmRef.current.value) {
      return setError("Hasła nie są takie same!")
    }
    try {
      setError("")
      setLoading(true)
      await signup(emailRef.current.value, passwordRef.current.value)
      axios.get('http://localhost:8080/users/register?email=' + emailRef.current.value )
      history.push("/konfigurator")
    } catch {
      setError("Nie udało się stworzyć konta")
    }

    setLoading(false)
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Avatar className={classes.avatar}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          Rejestracja
        </Typography>
        <form className={classes.form} noValidate>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                id="email"
                label="Adres Email"
                name="email"
                autoComplete="email"
                inputRef={emailRef}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label="Hasło"
                type="password"
                id="password"
                autoComplete="current-password"
                inputRef={passwordRef}
              />
            </Grid>
            <Grid item xs={12}>
            <TextField
                variant="outlined"
                required
                fullWidth
                name="password"
                label=" Potwierdź hasło "
                type="password"
                id="passworConfirm"
                autoComplete="Confirm-password"
                inputRef={passwordConfirmRef}
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleSubmit}
          >
            Zarejestruj
          </Button>
          <Button variant="contained" color="primary" fullWidth startIcon={<GoogleIcon/>}
            onClick={() => {
              var googleAuthProvider = new firebase.auth.GoogleAuthProvider();
              firebase.auth().signInWithPopup(googleAuthProvider).then((res) => {
                axios.get('http://localhost:8080/users/register?email=' + res.user.email)
                setLoginSuccess(true);
              }).catch(function(error) {
                console.log(error);
              });
            }}
          >
            Zarejestruj przez Google
          </Button>
          {error && <Alert severity="error">{error}</Alert>}
          <Grid container justify="flex-end">
            <Grid item>
              <Button disabled={loading} color="primary" onClick={()=>{history.push("/logowanie")}}>Posiasz już konto? Zaloguj się</Button>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={5}>
      </Box>
    </Container>
  );
}