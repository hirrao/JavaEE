import { useLocalStorage } from '@vueuse/core'

interface UserData {
  id?: number
  username?: string
  token?: string
  expiredAt?: number
  premissions?: number
}

export const createUserRepository = () => {
  const ref = useLocalStorage<UserData>('user', {})
  const isLoggedIn = () => {
    if (ref.value.token && ref.value.expiredAt) {
      return ref.value.expiredAt > Date.now()
    }
    return false
  }
  const isAdmin = () => ref.value.premissions === 1
  const clear = () => {
    ref.value = {
      id: undefined,
      username: undefined,
      token: undefined,
      expiredAt: undefined,
      premissions: undefined
    }
  }
  return { ref, isLoggedIn, isAdmin, clear }
}
