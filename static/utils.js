import Emitter from 'quill/core/emitter'
import { message } from 'antd'

/**
 * 覆写 Snow 的tooltip的save
 */
export function SnowTooltipSave () {
  const { value } = this.textbox
  const linkValidityRegex = /^(http|https)/
  switch (this.root.getAttribute('data-mode')) {
    case 'link': {
      if (!linkValidityRegex.test(value)) {
        message.error('链接格式错误，请输入链接 http(s)://...')
        return
      }
      const { scrollTop } = this.quill.root
      if (this.linkRange) {
        this.quill.formatText(this.linkRange, 'link', value, Emitter.sources.USER)
        delete this.linkRange
      } else {
        this.restoreFocus()
        this.quill.format('link', value, Emitter.sources.USER)
      }
      this.quill.root.scrollTop = scrollTop
      break
    }
    default:
  }
  this.textbox.value = ''
  this.hide()
}

export function SnowThemeLinkHandler (value) {
  if (value) {
    const range = this.quill.getSelection()
    if (range == null || range.length === 0) return
    let preview = this.quill.getText(range)
    if (/^\S+@\S+\.\S+$/.test(preview) && preview.indexOf('mailto:') !== 0) {
      preview = `mailto:${preview}`
    }
    const { tooltip } = this.quill.theme
    tooltip.save = SnowTooltipSave
    tooltip.edit('link', preview)
  } else {
    this.quill.format('link', false)
  }
}
