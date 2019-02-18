package com.softteco.roadqualitydetector.tasks;

import android.content.Context;
import android.text.TextUtils;

import com.softteco.roadqualitydetector.sqlite.model.BaseModel;
import com.softteco.roadqualitydetector.sqlite.model.FolderModel;
import com.softteco.roadqualitydetector.sqlite.model.RoadModel;
import com.softteco.roadqualitydetector.util.ExportToCSVResult;
import com.softteco.roadqualitydetector.util.FileUtils;
import com.softteco.roadqualitydetector.util.ZipTools;

import java.io.File;

public class ExportMeasurementDBIntoZIP extends ExportMeasurementDB {

    private BaseModel model;

    public ExportMeasurementDBIntoZIP(Context context, BaseModel model, final ExportToCSVResult listener) {
        super(context, listener);
        this.model = model;
    }

    @Override
    protected String[] doInBackground(final String... args) {
        String srcFolder = "";
        String zipName = "";
        if (model instanceof FolderModel) {
            exportFolderData((FolderModel) model);
            srcFolder = getSrcFolder((FolderModel) model, null);
            zipName = ((FolderModel) model).getName();
        } else if (model instanceof RoadModel) {
            FolderModel folder = folderDAO.getFolder(((RoadModel) model).getFolderId());
            if (folder != null) {
                exportRoadData(folder.getName(), (RoadModel) model);
                srcFolder = getSrcFolder(folder, (RoadModel) model);
                zipName = ((RoadModel) model).getName();
            }
        }
        if (!TextUtils.isEmpty(srcFolder)) {
            File outFile = FileUtils.createTmpFile(context, zipName + ZipTools.ZIP_EXT);
            ZipTools.createZipArchive(srcFolder, outFile);
            File zipFile = outFile.getAbsoluteFile();
            return new String[]{zipFile.getAbsolutePath()};
        } else {
            return new String[]{};
        }
    }

    private String getSrcFolder(FolderModel project, RoadModel road) {
        String folderName = "";
        if (project != null) {
            folderName += project.getName();
        }
        if (road != null) {
            if (project != null) {
                folderName += ZipTools.SLASH;
            }
            folderName += road.getName();
        }
        return FileUtils.getDataDir(folderName, true);
    }
}

