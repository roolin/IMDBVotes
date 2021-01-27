import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class IMDBDataFile<T> {
    static String imdbUrl = "https://datasets.imdbws.com/";

    private Class classType;
    private final String fileName;
    private File gzFile;
    private File tsvFile;

    public IMDBDataFile(Class c) {
        classType = c;
        FilesTypes ft = new FilesTypes<T>();
        fileName = ft.getFileName(c);
    }

    public void download() throws IOException {
        String gzFileName = fileName + ".gz";
        URL urlFile = new URL(imdbUrl + gzFileName);
        gzFile = File.createTempFile("IMDBRatings", gzFileName);
        try (InputStream in = urlFile.openStream()) {
            Files.copy(in, gzFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void unpack() throws IOException {
        FileInputStream is = new FileInputStream(gzFile);
        GZIPInputStream gzip = new GZIPInputStream(is);
        tsvFile = File.createTempFile("IMDBRatings", fileName);
        gzip.transferTo(new FileOutputStream(tsvFile));
    }

    public List<T> parseToTable() throws FileNotFoundException {
        return parseToTable(tsvFile);
    }

    public List<T> parseToTable(File file) throws FileNotFoundException {
        List<T> beans = new CsvToBeanBuilder<T>(new FileReader(file))
                .withSeparator('\t').withIgnoreQuotations(true).withType(classType).build().parse();
        return beans;
    }

    public File getTsvFile() {
        return tsvFile;
    }
}
