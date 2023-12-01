const {logger} = require("firebase-functions");
const {onRequest} = require("firebase-functions/v2/https");
const {onDocumentCreated} = require("firebase-functions/v2/firestore");
const { getAuth } = require("firebase-admin/auth");

// The Firebase Admin SDK to access Firestore.
const {initializeApp} = require("firebase-admin/app");
const {getFirestore} = require("firebase-admin/firestore");
const { user } = require("firebase-functions/v1/auth");

initializeApp();
const db = getFirestore();


exports.checkUserRegistration = onRequest(async (req, res) => {

  // Grab the text parameter.
  const userID = req.query.userID;

  //Get user from Firestore
  const userRef = db.collection('users').doc(userID);
  const doc = await userRef.get();

  if (!doc.exists) {
    const user = await getAuth().getUser(userID);
    const userData = {
        email   : user.email,
        name    : user.displayName
    }
    const result = await db.collection('users').doc(userID).set(userData);
    console.log("checkUseRegistration: User created");
    res.send('true')
  } else {
    console.log("checkUseRegistration: User found");
    res.send('true')
  }
  
});

exports.getUserRegistration = onRequest(async (req, res) => {

  // Grab the text parameter.
  const userID = req.query.userID;

  //Get user from Firestore
  const userRef = db.collection('users').doc(userID);
  const doc = await userRef.get();

  if (!doc.exists) {
    res.json(doc.data());
  } else {
    //res.send('true');
    res.send('NotFound');
  }
  
});

exports.getHome = onRequest(async (req, res) => {

  // Grab the text parameter.
  const userID = req.query.userID;

  //Get user from Firestore
  const userRef = db.collection('users').doc(userID);
  const doc = await userRef.get();

  if (!doc.exists) {
    res.send('false'); 
  } else {
    res.json(doc.data());
  }
  
});

