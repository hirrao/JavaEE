import { ElMessage } from 'element-plus'

export const doAction = async (promise: Promise<unknown>, label: string) => {
  try {
    await promise
    ElMessage.info(label + '成功')
  } catch (e) {
    ElMessage.error(label + '失败:' + (e as Error).message)
  }
}
