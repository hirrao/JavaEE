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

  const clear = () => {
    ref.value = {
      phoneNumber: '',
      messageCode: ''
    }
  }

  return { ref, clear }
}
