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
  let nrSafe = 0;
  for await (const line of rl) {
    const data = line.split(' ');

    let previous = data[0];
    isSafe = true;
    let direction = undefined;
    for (let i = 1; i < data.length; i++) {
      let dif = previous - data[i];

      if (dif === 0) {
        isSafe = false;
        break;
      }

      if (direction === undefined) {
        dif > 0 ? direction = 'down' : direction = 'up';
      } else {
        if (direction === 'down' && dif < 0) {
          isSafe = false;
          break;
        } else if (direction === 'up' && dif > 0) {
          isSafe = false;
          break;
        }
      }

      dif = Math.abs(dif);

      if (dif < 1 || dif > 3) {
        isSafe = false;
        break;
      }

      previous = data[i];
    }

    direction = undefined;

    if (isSafe) {
      nrSafe++;
    }
  }

  console.log(nrSafe);
}

async function star2(rl) {
  let nrSafe = 0;

  for await (const line of rl) {
    const data = line.split(' ').map(Number);

    const isLineSafe = (sequence) => {
      let direction = null;
      for (let i = 1; i < sequence.length; i++) {
        const diff = sequence[i] - sequence[i - 1];

        if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
          return false;
        }

        const currentDirection = diff > 0 ? 'up' : 'down';
        if (direction === null) {
          direction = currentDirection;
        } else if (direction !== currentDirection) {
          return false;
        }
      }
      return true;
    };

    if (isLineSafe(data)) {
      nrSafe++;
      continue;
    }

    let foundSafe = false;
    for (let i = 0; i < data.length; i++) {
      const modifiedLine = data.slice(0, i).concat(data.slice(i + 1));
      if (isLineSafe(modifiedLine)) {
        foundSafe = true;
        break;
      }
    }

    if (foundSafe) {
      nrSafe++;
    }
  }

  console.log(nrSafe);
}

processLineByLine();