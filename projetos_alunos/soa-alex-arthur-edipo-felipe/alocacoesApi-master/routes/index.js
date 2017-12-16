var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.render('index', { title: 'Express' });
});


router.get('/alocacoes', function (req, res, next) {
  var db = require('../db');
  var alocacoes = db.Mongoose.model('alocacoes', db.AlocacoesSchema, 'alocacoes');
  alocacoes.find({}).lean().exec(function (e, docs) {
    res.json(docs);
    res.end();
  });
});


router.post('/alocacoes/', function (req, res, next) {
  var db = require('../db');
  var Alocacoes = db.Mongoose.model('alocacoes', db.AlocacoesSchema, 'alocacoes');

  var novaAlocacao = new Alocacoes({ id_tarefa: req.body.tarefa, id_aluno: req.body.aluno });

  novaAlocacao.save(function (err) {
    if (err) {
      res.status(500).json({ error: err.message });
      res.end();
      return;
    }
    res.json(novaAlocacao);
    res.end();
  });
});

router.put('/alocacoes/:id', function (req, res, next) {
  var db = require('../db');
  var alocacao = db.Mongoose.model('alocacoes', db.AlocacoesSchema, 'alocacoes');
  alocacao.findOneAndUpdate({ _id: req.params.id }, req.body, { upsert: true }, function (err, doc) {
    if (err) {
      res.status(500).json({ error: err.message });
      res.end();
      return;
    }
    res.json(req.body);
    res.end();
  });
});

router.delete('/alocacoes/:id', function (req, res, next) {
  var db = require('../db');
  var alocacao = db.Mongoose.model('alocacoes', db.AlocacoesSchema, 'alocacoes');
  alocacao.find({ _id: req.params.id }).remove(function (err) {
    if (err) {
      res.status(500).json({ error: err.message });
      res.end();
      return;
    }
    res.json({ success: true });
    res.end();
  });
});

module.exports = router;
