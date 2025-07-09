import { useLocalStorage } from '@vueuse/core'
import { LSKey } from '@/data/storage/LocalStorage'

export interface AuthData {
  profiler?: AuthProfiler
  renewedAt?: number
}

export interface AuthProfiler {
  id: string
  username: string
  token: string
  expiresAt: number
}

export const createAuthRepository = () => {
  const authData = useLocalStorage<AuthData>(LSKey.Auth, {})

  const setProfiler = (token: string) => {}
  return
  {
    authData
  }
}
