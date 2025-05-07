import { createAuthRepository, createUserRepository } from './stores'

const lazy = <T>(factory: () => T) => {
  let value: T
  const get = () => {
    if (value === undefined) {
      value = factory()
    }
    return value
  }
  return get
}

export const Locator = {
  authRespository: lazy(createAuthRepository),
  userRepository: lazy(createUserRepository)
}
