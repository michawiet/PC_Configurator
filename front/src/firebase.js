import firebase from "firebase/app"
import "firebase/auth"

const app = firebase.initializeApp({
  apiKey: "AIzaSyCgY56gmqSDe_5i1AfZaMxJKn1xAiccW3E",
  authDomain: "pc-conf-login.firebaseapp.com",
  projectId: "pc-conf-login",
  storageBucket: "pc-conf-login.appspot.com",
  messagingSenderId: "660697311980",
  appId: "1:660697311980:web:30b54fcad593d1b22c13c9"
})

export const auth = app.auth()
export default app