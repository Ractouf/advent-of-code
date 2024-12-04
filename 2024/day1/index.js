const fs = require('fs');
const path = require('path');
const readline = require('readline');

function processLineByLine() {
  const filePath = path.join(__dirname, 'input.txt'); // Absolute path
  const fileStream = fs.createReadStream(filePath);

  const rl = readline.createInterface({
    input: fileStream,
    crlfDelay: Infinity,
  });

  /* star1(rl); */
  star2(rl);
}

async function star1(rl) {
  const leftList = [];
  const rightList = [];
  for await (const line of rl) {
    const [left, right] = line.split('   ');
    leftList.push(left);
    rightList.push(right);
  }

  leftList.sort((a, b) => a - b);
  rightList.sort((a, b) => a - b);

  let sum = 0;
  for (let i = 0; i < leftList.length; i++) {
    const left = +leftList[i];
    const right = +rightList[i];

    const list = [left, right].sort((a, b) => b - a);
    sum += list[0] - list[1];
  }

  console.log(sum);
}

async function star2(rl) {
  const leftList = [];
  const rightList = [];
  for await (const line of rl) {
    const [left, right] = line.split('   ');
    leftList.push(left);
    rightList.push(right);
  }

  let sum = 0;
  for (let i = 0; i < leftList.length; i++) {
    sum += +leftList[i] * rightList.filter(rl => +rl === +leftList[i]).length;
  }

  console.log(sum);
}

processLineByLine();