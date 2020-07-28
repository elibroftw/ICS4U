import subprocess
import os
import sys
from contextlib import suppress


OPTIONS = ['COMPILE', 'RUN', 'BUILD']
OPTION = 'COMPILE'
with suppress(IndexError):
    if sys.argv[1] == 'build': OPTION = 'BUILD'
    elif sys.argv[1] == 'run': OPTION = 'RUN'
    else: OPTION = 'COMPILE'


libs = {
    'javaFX': {
        'path': r'C:\Hard Drive\Other\java\javafx-14.0.1\lib',  # or %PATH_TO_FX%
        'modules': {
            'javafx.controls',
            'javafx.fxml'
        }
    }
}

MODULE_PATH = libs['javaFX']['path']
MODULES = ','.join(libs['javaFX']['modules'])

SRC = 'src/animation'
DEPENDENTS = ['src/animation/Animation.java', 'src/animation/SpriteData.java', 'src/animation/SpriteSheet.java']
DEPENDENTS = " ".join(DEPENDENTS)
MAIN_CLASS = 'src/animation/Main'
BUILD_DIR = 'build/'
COMPILE_DIR = f'{BUILD_DIR}compiled/'

if OPTION == 'COMPILE':
    # os.chdir(SRC)
    compile_cmd = f'javac {DEPENDENTS} --module-path {MODULE_PATH} --add-modules {MODULES} {MAIN_CLASS + ".java"}'
    print(compile_cmd)

    subprocess.call(compile_cmd)
elif OPTION == 'RUN':
    print(MAIN_CLASS.replace('/', '.'))
    run_cmd = f'java --module-path {MODULE_PATH} --add-modules {MODULES} {MAIN_CLASS}'
    subprocess.call(run_cmd)
elif OPTION == 'BUILD':
    pass
    # subprocess.call('jar cf ')