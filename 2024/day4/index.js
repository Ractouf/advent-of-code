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
  let lines = [];
  for await (const line of rl) {
    lines.push(line);
  }

  let sum = 0;
  for (let i = 0; i < lines.length; i++) {
    for (let j = 0; j < lines[i].length; j++) {
      if (lines[i][j] === 'X') {
        if (lines[i][j + 1] === 'M' && lines[i][j + 2] === "A" && lines[i][j + 3] === "S") {
          sum++;
        }

        if (lines[i][j - 1] === 'M' && lines[i][j - 2] === "A" && lines[i][j - 3] === "S") {
          sum++;
        }

        if ((lines[i + 1] && lines[i + 1][j] === 'M') && (lines[i + 2] && lines[i + 2][j] === "A") && (lines[i + 3] && lines[i + 3][j] === "S")) {
          sum++;
        }

        if ((lines[i - 1] && lines[i - 1][j] === 'M') && (lines[i - 2] && lines[i - 2][j] === "A") && (lines[i - 3] && lines[i - 3][j] === "S")) {
          sum++;
        }

        if ((lines[i + 1] && lines[i + 1][j + 1] === 'M') && (lines[i + 2] && lines[i + 2][j + 2] === "A") && (lines[i + 3] && lines[i + 3][j + 3] === "S")) {
          sum++;
        }

        if ((lines[i - 1] && lines[i - 1][j - 1] === 'M') && (lines[i - 2] && lines[i - 2][j - 2] === "A") && (lines[i - 3] && lines[i - 3][j - 3] === "S")) {
          sum++;
        }

        if ((lines[i + 1] && lines[i + 1][j - 1] === 'M') && (lines[i + 2] && lines[i + 2][j - 2] === "A") && (lines[i + 3] && lines[i + 3][j - 3] === "S")) {
          sum++;
        }

        if ((lines[i - 1] && lines[i - 1][j + 1] === 'M') && (lines[i - 2] && lines[i - 2][j + 2] === "A") && (lines[i - 3] && lines[i - 3][j + 3] === "S")) {
          sum++;
        }
      }
    }
  }

  console.log(sum);
}

async function star2(rl) {
  let lines = [];
  for await (const line of rl) {
    lines.push(line);
  }

  let sum = 0;
  for (let i = 0; i < lines.length; i++) {
    for (let j = 0; j < lines[i].length; j++) {
      if (lines[i][j] === 'A') {
        if (lines[i - 1] && lines[i + 1]) {
          if ((`${lines[i - 1][j - 1]}A${lines[i + 1][j + 1]}` === 'MAS' && `${lines[i + 1][j - 1]}A${lines[i - 1][j + 1]}` === 'MAS')
            || (`${lines[i - 1][j - 1]}A${lines[i + 1][j + 1]}` === 'MAS' && `${lines[i - 1][j + 1]}A${lines[i + 1][j - 1]}` === 'MAS')
            || (`${lines[i + 1][j + 1]}A${lines[i - 1][j - 1]}` === 'MAS' && `${lines[i - 1][j + 1]}A${lines[i + 1][j - 1]}` === 'MAS')
            || (`${lines[i + 1][j + 1]}A${lines[i - 1][j - 1]}` === 'MAS' && `${lines[i + 1][j - 1]}A${lines[i - 1][j + 1]}` === 'MAS')
          ) {
            sum++;
          }
        }
      }
    }
  }

  console.log(sum);
}

processLineByLine();