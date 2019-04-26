package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;

public class LocalizedUpload extends Upload implements LocaleChangeObserver {

    public LocalizedUpload() {
    }

    public LocalizedUpload(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        setI18n(createUpload());
    }

    public UploadI18N createUpload() {
        UploadI18N i18N = new UploadI18N();

        UploadI18N.AddFiles addFiles = new UploadI18N.AddFiles();
        addFiles.setMany(getTranslation("upload.addFiles.many"));
        addFiles.setOne(getTranslation("upload.addFiles.one"));

        UploadI18N.DropFiles dropFiles = new UploadI18N.DropFiles();
        dropFiles.setMany(getTranslation("upload.dropFiles.many"));
        dropFiles.setOne(getTranslation("upload.dropFiles.one"));

        UploadI18N.Error error = new UploadI18N.Error();
        error.setFileIsTooBig(getTranslation("upload.error.bigFile"));
        error.setIncorrectFileType(getTranslation("upload.error.incorrectType"));
        error.setTooManyFiles(getTranslation("upload.error.tooManyFiles"));

        UploadI18N.Uploading.RemainingTime remainingTime = new UploadI18N.Uploading.RemainingTime();
        remainingTime.setPrefix(getTranslation("upload.remainingTime.prefix"));
        remainingTime.setUnknown(getTranslation("upload.remainingTime.unknown"));

        UploadI18N.Uploading.Error uploadingError = new UploadI18N.Uploading.Error();
        uploadingError.setForbidden(getTranslation("upload.uploadingError.forbidden"));
        uploadingError.setServerUnavailable(getTranslation("upload.uploadingError.unavailable"));
        uploadingError.setUnexpectedServerError(getTranslation("upload.uploadingError.unexpected"));

        UploadI18N.Uploading.Status status = new UploadI18N.Uploading.Status();
        status.setConnecting(getTranslation("upload.status.connecting"));
        status.setHeld(getTranslation("upload.status.held"));
        status.setProcessing(getTranslation("upload.status.processing"));
        status.setStalled(getTranslation("upload.status.stalled"));

        UploadI18N.Uploading uploading = new UploadI18N.Uploading();
        uploading.setRemainingTime(remainingTime);
        uploading.setStatus(status);
        uploading.setError(uploadingError);

        i18N.setAddFiles(addFiles);
        i18N.setCancel(getTranslation("upload.cancel"));
        i18N.setDropFiles(dropFiles);
        i18N.setError(error);
        i18N.setUploading(uploading);
        i18N.setUnits(new UploadI18N.Units());
        return i18N;
    }
}
