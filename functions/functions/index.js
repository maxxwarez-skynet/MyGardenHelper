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

// Take the text parameter passed to this HTTP endpoint and query it from
// Firestore under the path /users/
exports.getUser = onRequest(async (req, res) => {

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
    res.json(userData);
  } else {
    //res.send('true');
    res.json(doc.data());
  }
  
});

exports.checkUseRegistration = onRequest(async (req, res) => {

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
    res.send('true')
  } else {
    res.send('true')
  }
  
});


exports.createUserRegistration = onRequest(async (req, res) => {

  // Grab the text parameter.
  //const userID = req.body;

/*   //Get user from Firestore
  const userRef = db.collection('users').doc(userID);
  const doc = await userRef.get();

  if (!doc.exists) {
    const user = await getAuth().getUser(userID);
    const userData = {
        email   : user.email,
        name    : user.displayName
    }
    const result = await db.collection('users').doc(userID).set(userData);
    res.json(userData);
  }  */
  res.send("odfd");
});

