const express = require('express')
const app = express()

app.use(express.static('front'));

app.get('/api/hello', function (req, res) {
  res.send({msg: 'Hello World!'})
})

// POST method route
app.post('/api/upload-specs', function (req, res) {
  res.send('POST request to the homepage');
});

app.listen(8080, function () {
  console.log('VideoGen back-end web server running...')
})

