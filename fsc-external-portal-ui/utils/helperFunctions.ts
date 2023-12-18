export const pipe =
  (...functions: any) =>
  (input: any) =>
    functions.reduce(
      (chain: any, func: any) => chain.then(func),
      Promise.resolve(input)
    );

export const compose =
  (...functions: any) =>
  (input: any) =>
    functions.reduceRight(
      (chain: any, func: any) => chain.then(func),
      Promise.resolve(input)
    );
