import { useLocalStorage } from '@vueuse/core'

interface UserData {
  id: string
  token: string
}

export const createUserRepository = () => {
  const ref = useLocalStorage<UserData>('user', {
    id: '',
    token: ''
  })
  return { ref }
}
