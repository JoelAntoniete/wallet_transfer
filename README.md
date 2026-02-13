# PicPay Simplificado – Implementação do Desafio Backend

## Visão Geral

Este repositório contém a minha implementação do **Desafio Técnico – Backend “PicPay Simplificado”**, proposto pela PicPay.

O desafio consiste em criar uma **API RESTful** que simula uma plataforma de pagamentos simplificada onde é possível depositar e realizar transferências de dinheiro entre usuários, respeitando um conjunto de regras de negócio.

O desafio original pode ser encontrado no link:  
📌 https://github.com/PicPay/picpay-desafio-backend

---

## Descrição do Desafio – PicPay Simplificado

Descrição oficial retirada do repositório do desafio:

> **Objetivo: PicPay Simplificado**  
> O PicPay Simplificado é uma plataforma de pagamentos simplificada. Nela é possível depositar e realizar transferências de dinheiro entre usuários. Temos 2 tipos de usuários, os comuns e lojistas, ambos têm carteira com dinheiro e realizam transferências entre eles.

---

## Requisitos do Desafio

A seguir estão os requisitos e regras de negócio que o sistema deve atender:

- Para ambos os tipos de usuário, é necessário ter **Nome Completo, CPF, e-mail e senha**.
- CPF e e-mail devem ser **únicos** no sistema.
- Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.
- Lojistas **só recebem transferências**, não enviam dinheiro.
- Deve validar se o usuário tem saldo antes de fazer uma transferência.
- Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo (mock):  
  `https://util.devi.tools/api/v2/authorize` utilizando o método **GET**.
- A operação de transferência deve ser **transacional**, ou seja, revertida em caso de qualquer inconsistência.
- Ao receber o pagamento, o usuário ou lojista precisa receber uma notificação (serviço externo mock):  
  `https://util.devi.tools/api/v1/notify` utilizando o método **POST**.
- O serviço deve ser **RESTFul**.

---

## Endpoint Principal

O fluxo de transferência implementado segue o contrato do desafio:

```http
POST /transfer
Content-Type: application/json
