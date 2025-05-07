import { useLocalStorage } from '@vueuse/core'
interface AuthData {
  phoneNumber: string
  messageCode: string
}

export const createAuthRepository = () => {
  const ref = useLocalStorage<AuthData>('auth', {
    phoneNumber: '',
    messageCode: ''
  })

  const set = (data: AuthData) => {
    ref.value = data
  }

  const clear = () => {
    ref.value = {
      phoneNumber: '',
      messageCode: ''
    }
  }

  return { ref, set, clear }
}
