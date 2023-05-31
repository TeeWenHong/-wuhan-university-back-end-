import sys
import os
import glob
import numpy as np
from PIL import Image
import warnings

warnings.filterwarnings("ignore", category=DeprecationWarning)

def perception_hash(image_path, hash_size=10):
    # 打开图片并将其转换为灰度图像
    img = Image.open(image_path).convert('L')
    # 缩小图片尺寸，减少计算量
    img = img.resize((hash_size, hash_size), Image.LANCZOS)
    # 获取像素值并转化为numpy数组，再reshape为二维数组
    pixels = np.array(img.getdata(), dtype=float).reshape((hash_size, hash_size))
    # 计算像素均值
    avg = pixels.mean()
    # 将所有大于均值的像素设为1，否则设为0
    diff = pixels > avg
    # 将二维数组展平成一维数组，并转化为整数型数据
    return diff.flatten().astype(int)

def hamming_distance(hash1,hash2):
    # 计算两个哈希值之间的汉明距离（即不同位数的个数）
    return np.count_nonzero(hash1 != hash2)

def compare_images(image_path1, folder_path, hash_size, threshold):
    hash1 = perception_hash(image_path1, hash_size=hash_size)
    # print(f"哈希值{hash1}")
    # 创建一个字典，用于存储每个图像的相似性和文件名
    similarities = {}
    for image_path2 in glob.glob(os.path.join(folder_path, "*.*")):
        if image_path2.endswith(".jpg") or image_path2.endswith(".png") or image_path2.endswith(".bmp"):
            # 对比图片1和文件夹中的每一张jpg/png/bmp图片
            hash2 = perception_hash(image_path2, hash_size=hash_size)  # 获取当前图片的哈希值
            distance = hamming_distance(hash1, hash2)  # 计算图片1和当前图片的汉明距离
            similarity = (len(hash1) - distance) / len(hash1) * 100  # 计算相似度
            image_name = os.path.basename(image_path2)  # 获取当前图片的文件名
            similarities[image_name] = (similarity, hash2)  # 将相似度和哈希值存入字典中

    # 按相似度对字典进行排序（降序）
    sorted_similarities = dict(sorted(similarities.items(), key=lambda x: x[1][0], reverse=True))

    # 首先输出相似度>阈值的图像，然后输出其余图像
    for image_name, (similarity, hash2) in sorted_similarities.items():
        if similarity > threshold:
            print("1")
            # print(f"{image_name}=={'pics{}%'.format(threshold)}=={similarity:.2f}%\n{hash2}")
            # print(\n{hash2})
        # print("0")

    # for image_name, (similarity, hash2) in sorted_similarities.items():
    #     if similarity <= threshold:
    #         print(f"{image_name}=={similarity:.2f}%\n{hash2}")

if __name__ == '__main__':
    image_path1 = sys.argv[1]
    # image_path1 = r"C:\Users\Huawei\Desktop\cahce\i.jpg"
    folder_path = r"C:\Users\Huawei\Desktop\qinshuge\src\userimg"
    hash_size = 10
    threshold = 90


    compare_images(image_path1, folder_path, hash_size=hash_size, threshold=threshold)
























# 这里是测试，不要看-----------------------------------------------------------------------------------------------------------
#
# import sys
# import os
# import glob
# import numpy as np
# from PIL import Image
# import warnings
#
# warnings.filterwarnings("ignore", category=DeprecationWarning)
#
# def perception_hash(image_path, hash_size):
#     img = Image.open(image_path).convert('L')
#     # 缩小图片尺寸，减少计算量
#     img = img.resize((hash_size, hash_size), Image.LANCZOS)
#     # 获取像素值并转化为numpy数组
#     pixels = np.array(img.getdata(), dtype=np.float32).reshape((hash_size, hash_size))
#     # 进行离散余弦变换（DCT）
#     dct_result = np.fft.fft2(pixels)
#     # 取DCT系数的低频部分，排除直流分量
#     dct_lowfreq = dct_result[:8, :8]
#     # 计算DCT系数的均值
#     avg = np.mean(dct_lowfreq)
#     # 将所有大于均值的系数设为1，否则设为0
#     diff = dct_lowfreq > avg
#     # 返回感知哈希结果（二进制数组）
#     return diff.flatten().astype(int)
#
# def hamming_distance(hash1,hash2):
#     # 计算两个哈希值之间的汉明距离（即不同位数的个数）
#     return np.count_nonzero(hash1 != hash2)
#
# def compare_images(image_path1, folder_path, hash_size, threshold):
#     hash1 = perception_hash(image_path1, hash_size=hash_size)
#     # print(f"哈希值{hash1}")
#     # 创建一个字典，用于存储每个图像的相似性和文件名
#     similarities = {}
#     for image_path2 in glob.glob(os.path.join(folder_path, "*.*")):
#         if image_path2.endswith(".jpg") or image_path2.endswith(".png") or image_path2.endswith(".bmp"):
#             # 对比图片1和文件夹中的每一张jpg/png/bmp图片
#             hash2 = perception_hash(image_path2, hash_size=hash_size)  # 获取当前图片的哈希值
#             distance = hamming_distance(hash1, hash2)  # 计算图片1和当前图片的汉明距离
#             similarity = (len(hash1) - distance) / len(hash1) * 100  # 计算相似度
#             image_name = os.path.basename(image_path2)  # 获取当前图片的文件名
#             similarities[image_name] = (similarity, hash2)  # 将相似度和哈希值存入字典中
#
#     # 按相似度对字典进行排序（降序）
#     sorted_similarities = dict(sorted(similarities.items(), key=lambda x: x[1][0], reverse=True))
#
#     # 首先输出相似度>阈值的图像，然后输出其余图像
#     for image_name, (similarity, hash2) in sorted_similarities.items():
#         if similarity > threshold:
#             print("1")
#             print(f"{image_name}=={'pics{}%'.format(threshold)}=={similarity:.2f}%\n{hash2}")
#             # print(\n{hash2})
#         print("0")
#
#     for image_name, (similarity, hash2) in sorted_similarities.items():
#         if similarity <= threshold:
#             print(f"{image_name}=={similarity:.2f}%\n{hash2}")
#
# if __name__ == '__main__':
#     # image_path1 = sys.argv[1]
#     image_path1 = r"C:\Users\Huawei\Desktop\img\原图.jpg"
#     folder_path = r"C:\Users\Huawei\Desktop\img\变形"
#     hash_size = 8
#     threshold = 90
#
#
#     compare_images(image_path1, folder_path, hash_size=hash_size, threshold=threshold)
#
#


# import sys
# import os
# import glob
# import sys
#
# import numpy as np
# from PIL import Image
#
#
# def perception_hash(image_path, hash_size=10):
#     # 打开图片并将其转换为灰度图像
#     img = Image.open(image_path).convert('L')
#     # 缩小图片尺寸，减少计算量
#     img = img.resize((hash_size, hash_size), Image.LANCZOS)
#     # 获取像素值并转化为numpy数组，再reshape为二维数组
#     pixels = np.array(img.getdata(), dtype=float).reshape((hash_size, hash_size))
#     # 计算像素均值
#     avg = pixels.mean()
#     # 将所有大于均值的像素设为1，否则设为0
#     diff = pixels > avg
#     # 将二维数组展平成一维数组，并转化为整数型数据
#     return diff.flatten().astype(int)
#
#
# def hamming_distance(hash1, hash2):
#     # 计算两个哈希值之间的汉明距离（即不同位数的个数）
#     return np.count_nonzero(hash1 != hash2)
#
#
# def compare_images(image_path1, folder_path, hash_size, threshold):
#     hash1 = perception_hash(image_path1, hash_size=hash_size)
#
#     # 创建一个字典，用于存储每个图像的相似性和文件名
#     similarities = {}
#     for image_path2 in glob.glob(os.path.join(folder_path, "*.*")):
#         if image_path2.endswith(".jpg") or image_path2.endswith(".png") or image_path2.endswith(".bmp"):
#             # 对比图片1和文件夹中的每一张jpg/png/bmp图片
#             hash2 = perception_hash(image_path2, hash_size=hash_size)  # 获取当前图片的哈希值
#             distance = hamming_distance(hash1, hash2)  # 计算图片1和当前图片的汉明距离
#             similarity = (len(hash1) - distance) / len(hash1) * 100  # 计算相似度
#             image_name = os.path.basename(image_path2)  # 获取当前图片的文件名
#             similarities[image_name] = (similarity, hash2)  # 将相似度和哈希值存入字典中
#
#     # 按相似度对字典进行排序（降序）
#     sorted_similarities = dict(sorted(similarities.items(), key=lambda x: x[1][0], reverse=True))
#
#     # 首先输出相似度>阈值的图像，然后输出其余图像
#     result = []
#     for image_name, (similarity, hash2) in sorted_similarities.items():
#         if similarity > threshold:
#             result.append((image_name, similarity, hash2))
#     for image_name, (similarity, hash2) in sorted_similarities.items():
#         if similarity <= threshold:
#             result.append((image_name, similarity, hash2))
#     return result
#
#
# if __name__ == '__main__':
#     image_path1 = r"C:\Users\Huawei\Desktop\img\原图.jpg"
#     folder_path = r"C:\Users\Huawei\Desktop\img"
#     hash_size = 10
#     threshold = 90
#
#     compare_images(image_path1, folder_path, hash_size=hash_size, threshold=threshold)


    # hash1 = [1111100001101111000010111100001011110000101111000000011100000000100000000111100001111110000111111001]
    # hash2 = [1111111111101111000010100100001011100011101111011000011100100000100011000111100001111111111000011000]
    # hash3 = "[1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 1 0 0 0 0 1 1 1 1 1 1 0 0 0]"
    # hash4 = "[1 1 1 1 1 0 0 1 1 1 1 1 1 0 1 1 0 0 1 1 1 1 0 1 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 1 0 0 0 0 1 0 1 0 1 1 0 0 1 1 1 0 0 0 1 1 1 0 0 1 1 1 0 1 1 1 0 0 0 0 0 1 0 1 1 0 1 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 1 0]"
    # test = hamming_distance(hash1, hash2)

    # test4 = "[1 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 1 0 1 1 1 1 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 1 1 1 1 1 1 0 0 0 0 1 1 1 1 1 1 0 0 0]"
    # test = perception_hash(image_path1, hash_size=10)
    # test2 = perception_hash(image_path1, hash_size=10)
    # test3 = hamming_distance(test, test2)
    # print(test)
