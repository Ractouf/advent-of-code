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
  let sum = 0;
  for await (const line of rl) {
    for (let result of line.matchAll(/mul\((\d+),(\d+)\)/g)) {
      [match, d1, d2] = result;

      sum += +d1 * +d2;
    }
  }

  console.log(sum);
}

async function star2(rl) {
  let text = '';
  for await (const line of rl) {
    text += line;
  }

  let dontIndexes = [];
  for (let dont of text.matchAll(/don't\(\)/g)) {
    dontIndexes.push(dont.index);
  }

  let doIndexes = [];
  for (let dos of text.matchAll(/do\(\)/g)) {
    doIndexes.push(dos.index);
  }

  console.log(doIndexes, dontIndexes);
  let sum = 0;
  for (let result of text.matchAll(/mul\((\d+),(\d+)\)/g)) {
    [match, d1, d2] = result;

    let matchingDonts = [];
    for (let dontIndex of dontIndexes) {
      if (dontIndex < result.index) {
        matchingDonts.push(dontIndex);
      }
    }

    let matchingDos = [];
    for (let doIndex of doIndexes) {
      if (doIndex < result.index) {
        matchingDos.push(doIndex);
      }
    }

    let maxDont = Math.max(...matchingDonts);
    let maxDo = Math.max(...matchingDos);

    let isDont = maxDont > maxDo;
    let isDo = maxDo > maxDont;

    if (isDont && !isDo) {
      continue;
    }

    sum += +d1 * +d2;
  }

  console.log(sum);
}

processLineByLine();