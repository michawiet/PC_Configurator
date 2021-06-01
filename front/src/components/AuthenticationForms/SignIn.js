import React, {useRef, useState}from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { useHistory } from "react-router-dom";
import GoogleButton from 'react-google-button'
import PropTypes from 'prop-types';
import { useAuth } from "../../AuthContext"
import Alert from '@material-ui/lab/Alert';
import firebase from "firebase/app";
import "firebase/auth";




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
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));


export default function SignIn() {
  const emailRef = useRef()
  const passwordRef = useRef()
  const { login } = useAuth()
  const [error, setError] = useState("")
  const [loading, setLoading] = useState(false)
  const classes = useStyles();
  let history = useHistory();




  async function handleSubmit(e) {
    e.preventDefault()

    try {

      setError("")
      setLoading(true)
      await login(emailRef.current.value, passwordRef.current.value)
      history.push("/loged")

    } catch {
      setError("Niepoprawne hasło lub e-mail")

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
          Zaloguj
        </Typography>
        <form className={classes.form} noValidate>
          <TextField variant="outlined" margin="normal" required fullWidth id="email" label="Adres Email" name="email" autoComplete="email" autoFocus inputRef={emailRef}/>

          <TextField variant="outlined" margin="normal" required fullWidth name="password" label="Hasło" type="password" id="password" autoComplete="current-password" inputRef={passwordRef}/>
          <FormControlLabel control={<Checkbox value="remember" color="primary" />} label="Zapamiętaj mnie"/>
          <Button disabled={loading}  fullWidth variant="contained" color="primary"className={classes.submit} onClick={handleSubmit}>
            Zaloguj
          </Button>
          {error && <Alert severity="error">{error}</Alert>}
          <GoogleButton variant="contained" color="primary"className={classes.submit}    
          onClick={() => {
                const googleAuthProvider = new firebase.auth.GoogleAuthProvider();
              firebase.auth().signInWithPopup(googleAuthProvider).then(function() {
                history.push("/loged");
              }).catch(function(error) {
                console.log(error);
              });
            }}
          />
          <Grid container>
            <Grid item xs>
            </Grid>
            <Grid item>
            <Button color="primary" onClick={()=>{history.push("/signup")}}>Nie masz konta? Zarejestruj się</Button>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={8}>
      </Box>
    </Container>
  );
}
