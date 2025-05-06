import { createAuthRepository } from "./stores";

const lazy = <T>(factory: () => T) => {
    let value: T;
    const get = () => {
      if (value === undefined) {
        value = factory();
      }
      return value;
    };
    return get;
  };
  

const Locator = {
    authRespository: lazy(createAuthRepository)
}