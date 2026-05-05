---
description: Build and Run StoryLand Project with Ant
---

# Steps to build and run the StoryLand project

1. **Ensure Apache Ant is installed**
   - Verify Ant is available by running `ant -version` in a terminal.
   - If not installed, download it from https://ant.apache.org/ and add it to your `PATH`.

2. **Navigate to the project directory**
   ```bash
   cd "C:\\Users\\hp\\StoryLand"
   ```

3. **Clean previous builds** // turbo
   ```bash
   ant clean
   ```

4. **Compile and build the project** // turbo
   ```bash
   ant build
   ```

5. **Run the project** // turbo
   ```bash
   ant run
   ```

6. **Optional: Debug the project**
   - Use `ant debug` if a debug target is defined in `nbproject/build-impl.xml`.
   - Alternatively, open the project in NetBeans and use its built‑in debugger.

7. **Verify execution**
   - The application should launch (e.g., a GUI window or console output).
   - Check the terminal for any errors and address them accordingly.

---

**Notes**:
- The `build.xml` imports `nbproject/build-impl.xml`, which contains the actual target definitions. The default targets (`clean`, `build`, `run`) are usually provided by NetBeans.
- If you encounter missing dependencies, ensure all required libraries are present in the `lib/` folder or referenced in the project properties.
- For Windows, you may need to run the commands in a *Developer Command Prompt* if environment variables are not globally set.
