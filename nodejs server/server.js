var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mysql = require('mysql');
const bcrypt = require('bcryptjs');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

app.get('/', function(req,res){
    return res.send({error:true, message:'Test Moneyhub Web API'})
});

var dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'moneyhubandorid'
});

dbConn.connect();

app.post('/user', function(req, res) {

    var user = req.body

    if(!user){
        return res.status(400).send({ error: true, message: 'Please provide user ' });
    }

    dbConn.query("INSERT INTO users SET ? ", user, function(error, results, fields){
        if(error) throw error;
        return res.send(results);
    });
});

app.get('/category', async function(req, res) {
    
    let idtransaction_types = req.body.idtransaction_types;

    dbConn.query('SELECT * FROM categories WHERE idtransaction_types = ?', idtransaction_types, function(error, results, fields) {
        if(error) throw error;
        return res.send(results);
    });
});

app.get('/allCareers', function(req, res) {
    dbConn.query('SELECT * FROM careers', function(error, results, fields) {
        if(error) throw error;
        return res.send(results);
    });
});

app.get('/allGenders', function(req, res) {
    dbConn.query('SELECT * FROM genders', function(error, results, fields) {
        if(error) throw error;
        return res.send(results);
    });
});

app.post('/login', async function(req, res) {

    let email = req.body.email;
    let password = req.body.password;

    if(!email || !password) {
        return res.status(400).send({ error: user, message: 'Please provide student id and password.' });
    }

    dbConn.query('SELECT * FROM users WHERE email = ?', [email], function(error, results, fields) {
        if(error) throw error;
        if(results[0]) {
            bcrypt.compare(password, results[0].password, function(error, result) {
                if(error) throw error;
                if(result) {
                    return res.send({"success": 1, "iduser": results[0].iduser, "email": results[0].email, "firstname": results[0].firstname,
                                     "lastname": results[0].lastname, "birthday": results[0].birthday, "profile_photo_path": results[0].profile_photo_path,
                                    "idcareer": results[0].idcareer, "idgender": results[0].idgender});
                } else {
                    return res.send({"success": 0});
                }
            });
        } else {
            return res.send({"success": 0});
        }
    });
});

app.post('/getUser', async function(req, res) {

    let email = req.body.email;

    if(!email) {
        return res.status(400).send({ error: true, message: 'Not have this email' });
    }
    dbConn.query(`
        SELECT users.iduser AS IDuser,
               users.firstname AS Firstname,
               users.lastname AS Lastname,
               users.birthday AS Birthday,
               users.profile_photo_path AS Photo_path,
               careers.career AS Career,
               genders.gender AS Gender 
        FROM users 
        JOIN careers ON users.idcareer = careers.idcareer
        JOIN genders ON users.idgender = genders.idgender
        WHERE email = ?`, 
        email, 
        function(error, results, fields) {
        if(error) throw error;
        if(results[0]) {
            return res.send(results[0]);
        } else{
            return res.status(400).send({ error: true, message: 'This email Not Found!!' });
        }
    });
});

app.post('/insertAccount', async function(req, res) {

    let post = req.body;
    let firstname = post.firstname;
    let lastname = post.lastname;
    let birthday = post.birthday;
    let email = post.email;
    let password = post.password;
    let idcareer = post.idcareer;
    let idgender = post.idgender;
    const salt = await bcrypt.genSalt(10);
    let password_hash = await bcrypt.hash(password, salt);

    if(!post) {
        return res.status(400).send({ error: true, message: 'Please provide a user data' });
    }

    dbConn.query('SELECT * FROM users WHERE email = ?', email, function(error, results, fields) {
        if(error) throw error;
        if(results[0]) {
            return res.status(400).send({ error: true, message: 'This email is already in database.' });
        } else {
            var insertUser = "INSERT INTO users(firstname, lastname, birthday, email, password, idcareer, idgender) VALUES('"
                               + firstname + "','" + lastname + "','" + birthday + "','" + email + "','" + password_hash + "','" + idcareer
                               + "','" + idgender + "')"

            dbConn.query(insertUser, (error, results) => {
                if(error) throw error;

                var insertBookofAccount = "INSERT INTO account_books(iduser, account_book, balance) VALUES('"
                                        + results.insertId + "','" + "สมุดบันทึกเริ่มต้น" + "','" + 0 + "')"

                dbConn.query(insertBookofAccount, (error, results) => {
                    if(error) throw error;
                    return res.send(results);
                });

                return res.send(results);
            });
        }
    });
});

app.post('/insertBookofAccount', async function(req, res) {

    let post = req.body;
    let iduser = post.iduser;
    let account_book = post.account_book;
    let account_photo_path = post.account_photo_path;

    if(!post) {
        return res.status(400).send({ error: true, message: 'Please provide a Book Of Account data' });
    }

    dbConn.query('SELECT * FROM account_books WHERE account_book = ?', account_book, function(error, results, fields) {
        if(error) throw error;
        if(results[0]) {
            return res.status(400).send({ error: true, message: 'This Book Of Account is already in database.' });
        } else {
            if(!account_photo_path) {
                var insertData = "INSERT INTO account_books(iduser, account_book) VALUES('"
                                    + iduser + "','" + account_book + "')"
            } else {
                var insertData = "INSERT INTO account_books(iduser, account_book, account_photo_path) VALUES('"
                                    + iduser + "','" + account_book + "','" + account_photo_path + "')"
            }

            dbConn.query(insertData, (error, results) => {
                if(error) throw error;
                return res.send(results);
            });
        }
    });
});

app.get('/allBookofAccount/:iduser', async function(req, res) {
    
    let iduser = req.params.iduser;

    dbConn.query('SELECT * FROM account_books WHERE iduser = ?', iduser, function(error, results, fields) {
        if(error) throw error;
        return res.send(results);
    });
});

app.post('/insertTransaction', async function(req, res) {

    let post = req.body;
    let idaccount_book = post.idaccount_book;
    let idcategory = post.idcategory;
    let amount = post.amount;
    let description = post.description;

    if(!post) {
        return res.status(400).send({ error: true, message: 'Please provide a Transaction data' });
    }

    var insertTransaction = "INSERT INTO transactions(idaccount_book, idcategory, amount, description) VALUES('"
                            + idaccount_book + "','" + idcategory + "','" + amount + "','" + description + "')"

    try {
        dbConn.query(insertTransaction, (error, results) => {
            if(error) throw error;
            dbConn.query(`
            SELECT transactions.idtransaction AS IDtransaction,
                   transactions.idaccount_book AS IDaccount_book,
                   transactions.idcategory AS IDcategory,
                   transactions.amount AS Amount,
                   categories.idtransaction_types AS IDtransaction_type,
                   account_books.balance AS Balance 
            FROM transactions 
            JOIN categories ON transactions.idcategory = categories.idcategory
            JOIN account_books ON transactions.idaccount_book = account_books.idaccount_book
            WHERE transactions.idaccount_book = ? 
            ORDER BY IDtransaction DESC LIMIT 1`, 
            idaccount_book, 
            function(error, results, fields) {
                if (error) throw error;
                if (results[0].IDtransaction_type == 1) {
                    dbConn.query(`UPDATE account_books SET balance = ? WHERE idaccount_book = ?`, [results[0].Balance + results[0].Amount, results[0].IDaccount_book], function(error, results, fields) {
                        if(error) throw error;
                        return res.send({ error: false, message: 'Transaction & Account_book has been inserted & updated successfully' });
                    });
                } else {
                    dbConn.query(`UPDATE account_books SET balance = ? WHERE idaccount_book = ?`, [results[0].Balance - results[0].Amount, results[0].IDaccount_book], function(error, results, fields) {
                        if(error) throw error;
                        return  res.send({ error: false, message: 'Transaction & Account_book has been inserted & updated successfully' });
                    });
                }
        });
    });
    } catch (error) {
        return res.status(400).send({
            success : 0,
            results : error
        })
    }
    
});

app.get('/allTransaction', async function (req, res) {

    let idaccount_book = req.body.idaccount_book;

    dbConn.query(`
        SELECT transactions.idtransaction AS IDtransaction,
               transactions_types.type AS Transaction_Type,
               categories.category AS Category,
               transactions.amount AS Amount,
               transactions.description AS Description,
               transactions.created_at AS Created_at
        FROM transactions
        JOIN categories ON transactions.idcategory = categories.idcategory
        JOIN transactions_types ON categories.idtransaction_types = transactions_types.idtransaction_types
        WHERE transactions.idaccount_book = ?`,
        idaccount_book,
        function (error, results, fields) {
            if (error) throw error;
            return res.send(results);
        }
    );
});

app.get('/transaction', async function (req, res) {

    let idtransaction = req.body.idtransaction;
    
    dbConn.query(`
        SELECT transactions.idtransaction AS IDtransaction,
               transactions_types.type AS Transaction_Type,
               categories.category AS Category,
               transactions.amount AS Amount,
               transactions.description AS Description,
               transactions.created_at AS Created_at
        FROM transactions
        JOIN categories ON transactions.idcategory = categories.idcategory
        JOIN transactions_types ON categories.idtransaction_types = transactions_types.idtransaction_types
        WHERE transactions.idtransaction = ?`,
        idtransaction,
        function (error, results, fields) {
            if (error) throw error;
            return res.send(results);
        }
    );
});

app.put('/updateTransaction', async function (req, res) {

    let idtransaction = req.body.idtransaction;
    let transdata = req.body;

    dbConn.query('UPDATE transactions SET ? WHERE idtransaction = ?', [transdata, idtransaction], function(error, results, fields) {
        if(error) throw error;
        
        return res.send({ error: false, message: 'Transaction has been updated successfully' });
    });
});

app.delete('/deleteTransaction', async function (req, res) {

    let idtransaction = req.body.idtransaction;

    dbConn.query('DELETE FROM transactions WHERE idtransaction = ?', idtransaction, function(error, results, fields) {
        if(error) throw error;
        
        return res.send({ error: false, message: 'Transaction has been deleted successfully' });
    });
});


//set port
app.listen(3000, function(){
    console.log('Node app is running on port 3000');
});

module.exports = app;