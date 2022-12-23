/**
 * @license Copyright (c) 2014-2022, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see LICENSE.md or https://ckeditor.com/legal/ckeditor-oss-license
 */
import ClassicEditor from '@ckeditor/ckeditor5-editor-classic/src/classiceditor.js';
import Autoformat from '@ckeditor/ckeditor5-autoformat/src/autoformat.js';
import BlockQuote from '@ckeditor/ckeditor5-block-quote/src/blockquote.js';
import Bold from '@ckeditor/ckeditor5-basic-styles/src/bold.js';
import CloudServices from '@ckeditor/ckeditor5-cloud-services/src/cloudservices.js';
import Essentials from '@ckeditor/ckeditor5-essentials/src/essentials.js';
import FontSize from '@ckeditor/ckeditor5-font/src/fontsize.js';
import Heading from '@ckeditor/ckeditor5-heading/src/heading.js';
import HorizontalLine from '@ckeditor/ckeditor5-horizontal-line/src/horizontalline.js';
import Image from '@ckeditor/ckeditor5-image/src/image.js';
import ImageInsert from '@ckeditor/ckeditor5-image/src/imageinsert.js';
import ImageResize from '@ckeditor/ckeditor5-image/src/imageresize.js';
import ImageToolbar from '@ckeditor/ckeditor5-image/src/imagetoolbar.js';
import ImageUpload from '@ckeditor/ckeditor5-image/src/imageupload.js';
import Indent from '@ckeditor/ckeditor5-indent/src/indent.js';
import Italic from '@ckeditor/ckeditor5-basic-styles/src/italic.js';
import Link from '@ckeditor/ckeditor5-link/src/link.js';
import LinkImage from '@ckeditor/ckeditor5-link/src/linkimage.js';
import List from '@ckeditor/ckeditor5-list/src/list.js';
import Paragraph from '@ckeditor/ckeditor5-paragraph/src/paragraph.js';
import PasteFromOffice from '@ckeditor/ckeditor5-paste-from-office/src/pastefromoffice.js';
import Table from '@ckeditor/ckeditor5-table/src/table.js';
import TextTransformation from '@ckeditor/ckeditor5-typing/src/texttransformation.js';
import SimpleUploadAdapter from '@ckeditor/ckeditor5-upload/src/adapters/simpleuploadadapter';

class Editor extends ClassicEditor {}

// Plugins to include in the build.
Editor.builtinPlugins = [
	Autoformat,
	BlockQuote,
	Bold,
	CloudServices,
	Essentials,
	FontSize,
	Heading,
	HorizontalLine,
	Image,
	ImageInsert,
	ImageResize,
	ImageToolbar,
	ImageUpload,
	Indent,
	Italic,
	Link,
	LinkImage,
	List,
	Paragraph,
	PasteFromOffice,
	SimpleUploadAdapter,
	Table,
	TextTransformation
];

// Editor configuration.
Editor.defaultConfig = {
	toolbar: {
		items: [
			'heading',
			'|',
			'bold',
			'italic',
			'fontSize',
			'bulletedList',
			'numberedList',
			'|',
			'outdent',
			'indent',
			'|',
			'horizontalLine',
			'blockQuote',
			'insertTable',
			'undo',
			'redo',
			'link',
			'imageUpload'
		]
	},
	language: 'ko',
	image: {
		toolbar: [
			'imageTextAlternative',
			'linkImage'
		]
	}
};

export default Editor;
